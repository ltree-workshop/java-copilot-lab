package com.example.copilotlab;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    private static final int PORT = 8080;
    private final UserService userService = new UserService();

    public static void main(String[] args) throws IOException {
        new App().start();
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", PORT), 0);
        server.createContext("/", this::handleIndex);
        server.start();
        System.out.printf("Java Copilot Lab running at http://localhost:%d%n", PORT);
    }

    private void handleIndex(HttpExchange exchange) throws IOException {
        Map<String, String> query = parseQuery(exchange.getRequestURI().getRawQuery());
        String searchText = query.getOrDefault("search", "");
        List<User> users = userService.findUsers(searchText);
        String response = renderPage(searchText, users);

        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream output = exchange.getResponseBody()) {
            output.write(bytes);
        }
    }

    private String renderPage(String searchText, List<User> users) {
        String results = users.isEmpty()
                ? "<p>No users matched your search.</p>"
                : users.stream()
                        .map(user -> "<li><strong>" + escape(user.name()) + "</strong><span>"
                                + escape(user.role()) + "</span><small>" + escape(user.team()) + " team</small></li>")
                        .collect(Collectors.joining());

        return """
                <!doctype html>
                <html lang="en">
                <head>
                  <meta charset="utf-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>Java Copilot Lab</title>
                  <style>
                    body { margin: 0; font-family: "Segoe UI", system-ui, sans-serif; background: #f5f7fa; color: #1f2328; }
                    main { width: min(960px, calc(100%% - 32px)); margin: 48px auto; }
                    .hero { padding: 32px; background: #24292f; color: #fff; border-radius: 18px; }
                    .eyebrow { margin: 0 0 8px; color: #8ec5ff; font-weight: 700; text-transform: uppercase; letter-spacing: .08em; }
                    .card { margin-top: 24px; padding: 28px; background: #fff; border: 1px solid #d0d7de; border-radius: 18px; }
                    label { display: block; margin-bottom: 8px; font-weight: 700; }
                    .form-row { display: flex; gap: 10px; }
                    input { flex: 1; padding: 10px 12px; border: 1px solid #d0d7de; border-radius: 8px; font-size: 1rem; }
                    button { padding: 10px 16px; border: 0; border-radius: 8px; background: #0078d4; color: #fff; font-weight: 700; }
                    .notice { margin-top: 16px; padding: 12px; background: #eaf4ff; border: 1px solid #b6ddff; border-radius: 8px; }
                    ul { padding: 0; list-style: none; }
                    li { display: grid; gap: 4px; margin: 10px 0; padding: 14px; border: 1px solid #d0d7de; border-radius: 10px; }
                    span, small { color: #57606a; }
                  </style>
                </head>
                <body>
                  <main>
                    <section class="hero">
                      <p class="eyebrow">Modern Java Development</p>
                      <h1>Java Copilot Lab</h1>
                      <p>Hello from Java inside a devcontainer.</p>
                    </section>
                    <section class="card">
                      <h2>User search</h2>
                      <p>Use this small app to practice Copilot-assisted explanation, validation, tests, and review.</p>
                      <form method="get" action="/">
                        <label for="search">Search users</label>
                        <div class="form-row">
                          <input id="search" name="search" type="text" value="%s" placeholder="Try Ada, Grace, or Linus">
                          <button type="submit">Search</button>
                        </div>
                      </form>
                      <p class="notice">%s</p>
                      <h3>Results</h3>
                      <ul>%s</ul>
                    </section>
                  </main>
                </body>
                </html>
                """.formatted(
                escape(searchText),
                searchText == null || searchText.isBlank()
                        ? "Enter a name to filter the sample user list."
                        : "Showing matches for <strong>" + escape(searchText) + "</strong>.",
                results);
    }

    private Map<String, String> parseQuery(String rawQuery) {
        if (rawQuery == null || rawQuery.isBlank()) {
            return Map.of();
        }

        return List.of(rawQuery.split("&")).stream()
                .map(pair -> pair.split("=", 2))
                .collect(Collectors.toMap(
                        pair -> decode(pair[0]),
                        pair -> pair.length > 1 ? decode(pair[1]) : "",
                        (first, second) -> second));
    }

    private String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
