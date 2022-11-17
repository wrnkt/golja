JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: Cell.class

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) Cell

clean:
	$(RM) $(CLASSDIR)/*.class
