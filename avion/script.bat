SET temp=D:\temp
SET bin=bin
SET lib=lib
SET web=templates\
SET xml=web.xml
SET webapps="C:\Program Files\Apache Software Foundation\Tomcat 11.0\webapps"
SET project=avion


MD tempjava;
FOR /R "src" %%a IN (*.java) DO copy "%%a" "tempjava"
cd tempjava
javac -parameters -d  ../%bin% *.java

cd ..
RD /s %temp% 
MD %temp%
MD %temp%\WEB-INF

XCOPY /s %lib%  %temp%\WEB-INF\lib  
XCOPY /s %bin%   %temp%\WEB-INF\classes
XCOPY /s %web%  %temp%\web   
XCOPY %xml%   %temp%\WEB-INF\

cd %temp%

jar cvf %webapps%\%project%.war WEB-INF  web
