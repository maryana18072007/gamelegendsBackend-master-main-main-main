@echo off
echo Iniciando sistema Game Legends...
echo.

echo 1. Verificando se o SQL Server está rodando...
sc query MSSQLSERVER | find "RUNNING" >nul
if %errorlevel% neq 0 (
    echo SQL Server não está rodando. Iniciando...
    net start MSSQLSERVER
) else (
    echo SQL Server já está rodando.
)

echo.
echo 2. Compilando e iniciando o backend...
cd /d "Z:\TCC\gamelegendsBackend-master-main-main-main"
call mvnw.cmd clean compile
call mvnw.cmd spring-boot:run

pause