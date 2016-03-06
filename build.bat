@if not exist classes mkdir classes
javac -target 1.7 -source 1.7 -d classes -O -classpath src;MegaMek.jar src/megameklab/com/*.java
jar cmf manifest.txt MegaMekLab.jar -C classes .
