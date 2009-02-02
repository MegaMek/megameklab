@if not exist classes mkdir classes
javac -target 1.6 -source 1.6 -d classes -classpath src;lib/TinyXML.jar;MegaMek.jar src/megameklab/com/*.java
jar cmf ./manifest.txt MegaMekLab.jar -C classes .