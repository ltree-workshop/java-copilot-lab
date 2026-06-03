# Java Copilot Lab

This starter repository is the Java alternative exercise for the  
**Modern ColdFusion Development with GitHub Copilot**  
workshop module.

It uses:
- Java 21
- Maven
- VS Code Dev Containers
- GitHub Copilot
- GitHub Copilot Chat
- Java Extension Pack
- SonarLint

## Start the lab

Fork this repo into your own user github account


Verify the environment:

```bash
java -version
mvn -version
```

Run tests:

```bash
mvn test
```

The current unit tests include search coverage for blank input, case-insensitive matching, role-based matching, and no-result behavior.

## Run the app in GitHub Codespaces

If you are running this lab in **GitHub Codespaces**, start the app the same way:

```bash
mvn exec:java
```

When the app starts on port `8080`, Codespaces should detect the port automatically and offer to open it in the browser.

You can also open it manually from the **Ports** panel in VS Code:

1. Find port `8080`
2. Make sure it is forwarded
3. Open the forwarded URL in your browser

Important:
- Do not use `http://localhost:8080` from your local machine for a Codespace
- Use the forwarded Codespaces URL for port `8080` instead

After the app opens in the browser, try the same search by appending:

```text
/?search=ada
```

Example:

```text
https://<your-codespace-url>?search=ada
```

## Notes

- Local VS Code and GitHub Codespaces use the same Maven command to run the app.
- The difference is the URL you open in the browser: `localhost` for local development, forwarded port URL for Codespaces.
