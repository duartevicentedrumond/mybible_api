@echo off

:: Get today's date
for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
    set day=%%i
    set month=%%j
    set year=%%k
)
set datestr=%year%_%month%_%day%

:: Get backup directory
set backup_directory=%C:\Users\duarte\Documents\me\my_backup\

:: Get backup file name
set backup_name=my_bible_%datestr%

:: Get complete backup url
set backup_url=%backup_directory%%backup_name%

:: Backup my_bible database into backup file
pg_dump -U postgres -h localhost -p 5433 -Fc -d my_bible > %backup_url%

:: Get last written file in backup_directory and restore it to my_bible_test database
FOR /F "tokens=*" %%i IN ('dir "%backup_directory%" /b /a-d /t:w /o-d') DO (
    SET a=%%i
    GOTO :found
)
echo No subfolder found
goto :eof
:found

pg_restore --clean -h localhost -p 5433 -U postgres -Fc -d my_bible_test %backup_directory%%a%

:: Freeze console window
::cmd /k