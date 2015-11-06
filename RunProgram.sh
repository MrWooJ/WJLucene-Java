#!/bin/bash
LIBPATH=""
PROJECTDIR=$(pwd)
SRCDIR=$PROJECTDIR"/src/com/lucene"
BINDIR=$PROJECTDIR"/bin"
BINPATH=$PROJECTDIR"/bin/com/lucene"
RUNPATH="com.lucene.ViewController"

function GenerateLibrariPath {
	local DIR=$PROJECTDIR"/JarFiles/"
	local JXL=$DIR"jxl.jar"
	local LUCENEANALYZER=$DIR"lucene-analyzers-3.6.2.jar"
	local POI=$DIR"poi-3.9.jar"
	local SWINGX=$DIR"swingx-all-1.6.4.jar"
	local LUCENECORE=$DIR"lucene-core-3.6.2.jar"
	local COMMONIO=$DIR"commons-io-2.4.jar"
	local JAVAFX=$DIR"javafx-ui-swing.jar"
	LIBPATH=.:$JXL:$LUCENEANALYZER:$POI:$SWINGX:$LUCENECORE:$COMMONIO:$JAVAFX:$BINDIR
}
GenerateLibrariPath
#echo "LibraryPath: "$LIBPATH

cd $SRCDIR
#echo "SRC PWD: "$(pwd)
javac -d $BINDIR -cp $LIBPATH *.java

cd $PROJECTDIR
#echo "BIN PWD: "$(pwd)
java -cp $LIBPATH $RUNPATH $PROJECTDIR