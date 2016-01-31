@ECHO OFF
cd "D:\Libraries\Documents\Workspaces\GPFlow\src"
ECHO Compiling FlowFX...
javac -d "../bin" -classpath "../.google_apis/youtube-v3r152lv1.18.0-rc/youtube/libs/*;../.google_apis/youtube-v3r152lv1.18.0-rc/youtube/*;." x/rdr/*.java x/rdr/fx/*.java x/rdr/fx/css/*.java
cd "../bin"
IF EXIST x/rdr/fx/FlowFX.class (
	ECHO Running FlowFX...
	ECHO -----------------------------------------------
	java -classpath "../.google_apis/youtube-v3r152lv1.18.0-rc/youtube/libs/*;../.google_apis/youtube-v3r152lv1.18.0-rc/youtube/*;." x.rdr.fx.FlowFX
	ECHO -----------------------------------------------
)