# Digital Logic Circuit Simulator

Third-semester Core Java / Swing desktop application.

## Features
- Seven gates: AND, OR, NOT, NAND, NOR, XOR, XNOR
- Half Adder, Full Adder, 2:1 and 4:1 Multiplexers, 2:4 Decoder, 1-bit Comparator
- Interactive binary inputs, automatic truth tables, Boolean expressions
- Dark-themed GUI and circuit visualizations

## Run (Windows PowerShell, from the project root)

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
java -cp out Main
```

Or run `run.bat` by double-clicking it (requires JDK on PATH).

## Notes
Basic logic-gate symbols are simplified. Complex circuits are shown as labeled functional blocks rather than internal gate-level wiring. No database is required.
