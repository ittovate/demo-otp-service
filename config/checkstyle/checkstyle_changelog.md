# Changelog:
- Ignore `HideField` warnings in constructor and setters.
- Enable `@SuppressWarnings` to suppress the warning on `OtpServiceApplication` class.
- Remove `JavadocPackage` to avoid an error with JavaDocs on packages.
- Set max line length to 120 (To match default IntelliJ IDEA formatting)
- Configure import order to match default IntelliJ IDEA formatter configuration (tested on v14):
  - Group of static imports is on the bottom.
  - Groups of non-static imports: all imports except of "javax" and "java", then "javax" and "java".
  - imports will be sorted in the groups.
  - Groups are separated by one blank line.
