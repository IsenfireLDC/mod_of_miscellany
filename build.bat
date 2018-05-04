@echo off
setlocal enableextensions enabledelayedexpansion
goto runBuild

:runBuild
call gradlew build
goto copyFiles

:setTxt
cd .\docs
call genTxtFiles
goto end


:copyFiles
set count=0
set inAll=0
for /f "delims=" %%i in ('dir /b /o:-d ".\build\libs\*.jar"') do (
	if "!count!"=="2" (
		goto setTxt
	) else (
		echo Checking for existance
		if exist .\docs\builds\all-versions\%%i (
			echo File %%i already exists in destination folder all-versions
			set inAll=1

			if exist .\docs\builds\%~n1\%%i (
				echo File %%i already exists in destination folder %~n1
				exit /b 1
			)
		)
		if "!inAll!"=="0" (
			copy /y .\build\libs\%%i .\docs\builds\all-versions\%%i
			echo %~n0: Copied file %%i to docs\builds\all-versions\%%i
		)
		if /i "%~n1" NEQ "" (
			copy /y .\build\libs\%%i .\docs\builds\%~n1\%%i
			echo %~n0: Copied file %%i to docs\builds\%~n1\%%i
		)
		set /a count += 1
	)
)
goto setTxt

:end