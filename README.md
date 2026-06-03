# Java Copilot Lab

This starter repository is the Java alternative exercise for the **Modern ColdFusion Development with GitHub Copilot** workshop module.

It uses:

- Java 21
- Maven
- VS Code Dev Containers
- GitHub Copilot
- GitHub Copilot Chat
- Java Extension Pack
- SonarLint

## Start the lab

Open this folder in VS Code and choose:

```text
Dev Containers: Reopen in Container
```

Verify the environment:

```bash
java -version
mvn -version
```

Run tests:

```bash
mvn test
```

Start the app:

```bash
mvn exec:java
```

Open:

```text
http://localhost:8080
```

Try a search:

```text
http://localhost:8080/?search=ada
```

## Suggested Copilot prompts

```text
Explain this Java class in plain English. Focus on responsibilities, inputs, outputs, assumptions, and edge cases. Do not suggest code changes yet.
```

```text
Suggest the smallest safe improvement to this class. Keep behavior easy to test. Do not introduce a framework or rewrite the application.
```

```text
Suggest additional JUnit tests for UserService. Include happy path, empty input, case-insensitive search, and no-match behavior. Keep the tests simple.
```

```text
Summarize the changes in this branch as a pull request description. Include Summary, Testing, and Risk sections. Keep it factual and do not overstate the impact.
```

## Responsible Copilot use

- Ask Copilot to explain before changing code.
- Keep changes small and reviewable.
- Run `mvn test` before committing.
- Do not paste secrets, credentials, or production data.
- Review generated code manually.
- Use branches and pull requests for AI-assisted changes.
