JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	LabVier.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

MAIN = LabVier

run: classes
	$(JVM) $(MAIN)
