@echo off
setlocal

cd /d "%~dp0"

echo ======================================
echo  DIGITAL LOGIC CIRCUIT SIMULATOR
echo ======================================

echo.
echo Compiling Java files...

if not exist "out" mkdir "out"

if errorlevel 1 (
    echo Failed to create output directory.
    pause
    exit /b 1
)

javac -encoding UTF-8 -d "out" ^
src\Main.java ^
src\gates\*.java ^
src\circuits\*.java ^
src\gui\*.java

if errorlevel 1 (
    echo.
    echo Compilation failed.
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo Starting simulator...
echo.

java -cp "out" Main

if errorlevel 1 (
    echo.
    echo Application failed to start.
)

pause