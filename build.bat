@echo off
setlocal enableextensions enabledelayedexpansion
goto runBuild

:runBuild
call gradlew build
goto copyFiles

:setTxt
call .\docs\genTxtFiles.bat
goto end


:copyFiles
set count=0
for /f "delims=" %%i in ('dir /b /o:-d ".\build\libs\*.jar"') do (
	if "!count!"=="2" (
		goto setTxt
	) else (
		if exist .\docs\builds\dev-versions\%%i (
			echo File %%i already exists in the destination folder
			exit /b 1
		)
		copy /y .\build\libs\%%i .\docs\builds\dev-versions\%%i
		echo %~n0: Copied file %%i to docs\builds\dev-versions\%%i
		set /a count += 1
	)
)

:end