@echo off
echo Starting MegaMekLab...

if exist gradlew.bat (
    echo Using Gradle wrapper...
    call gradlew.bat run
) else (
    echo Using system Gradle...
    gradle run
)

if %ERRORLEVEL% neq 0 (
    echo Failed to start MegaMekLab
    pause
    exit /b %ERRORLEVEL%
)