@echo off

where java 2>nul >nul
IF %ERRORLEVEL% NEQ 0 (
  echo Missing Java executable, please install the JRE, if already installed make sure it can be reached from current environment.
  exit /b %ERRORLEVEL%
)

java -jar template.jar %1 %2 %3 %4 %5 %6 %7 %8 %9
