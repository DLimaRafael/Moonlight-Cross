@ECHO OFF

REM === FOR REMOVING UNDESIRED .class files:
REM for /r "src" %%f in (*.class) do del "%%f"

REM === ENTERS THE src FOLDER AND COMPILES IT TO THE bin FOLDER:
REM use it from outside of src as just ".\build.bat"
javac -cp "src" -d "bin" "src/engine/Main.java"
java -cp bin engine.Main