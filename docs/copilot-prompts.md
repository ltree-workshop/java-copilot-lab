# Copilot Prompt Library for Java

## Explain before changing

```text
Explain this Java class in plain English. Focus on responsibilities, inputs, outputs, assumptions, and edge cases. Do not suggest code changes yet.
```

## Small safe improvement

```text
Suggest the smallest safe improvement to this class. Keep behavior easy to test. Do not introduce a framework or rewrite the application.
```

## Improve tests

```text
Suggest additional JUnit tests for UserService. Include happy path, empty input, case-insensitive search, and no-match behavior. Keep the tests simple.
```

## Review risk

```text
Review this Java change for correctness, null handling, maintainability, and test coverage. Do not rewrite it.
```

## PR summary

```text
Summarize the changes in this branch as a pull request description. Include Summary, Testing, and Risk sections. Keep it factual and do not overstate the impact.
```

## Manual test checklist

```text
Suggest a small manual test checklist for this pull request. Include happy path, blank search, no-match search, and app startup.
```
