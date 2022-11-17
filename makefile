JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: CellBoard.class

CellBoard.class: CellBoard.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) CellBoard.java

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) CellBoard

clean:
	$(RM) $(CLASSDIR)/*.class
