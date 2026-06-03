# Manual Test Plan

## Environment

- [ ] Reopen the repository in the devcontainer.
- [ ] Confirm `java -version` reports Java 21.
- [ ] Confirm `mvn -version` works.

## Automated tests

- [ ] Run `mvn test`.
- [ ] Confirm all tests pass.
- [ ] Confirm unit tests cover blank search, case-insensitive search, role search, and no-match behavior.

## App startup

- [ ] Run `mvn exec:java`.
- [ ] Open `http://localhost:8080`.
- [ ] Confirm the page title says `Java Copilot Lab`.

## Search behavior

- [ ] Submit an empty search and confirm the full sample user list appears.
- [ ] Search for `Ada` and confirm Ada Lovelace appears.
- [ ] Search for `Platform` and confirm Grace Hopper appears.
- [ ] Search for a value that does not exist and confirm the empty result message appears.

## Copilot-assisted change verification

- [ ] Review the diff before committing.
- [ ] Confirm generated code does not introduce unnecessary frameworks.
- [ ] Confirm generated code handles null or blank values where relevant.
- [ ] Confirm tests still pass.
- [ ] Confirm the app still starts.

## Pull request checklist

- [ ] Pull request summary explains the change.
- [ ] Testing notes include `mvn test`.
- [ ] Risk notes include null handling, maintainability, or scope.
- [ ] Human reviewer can understand the diff.
