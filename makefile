JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: CellBoard.class

rebuild:
	make clean
	make

CellBoard.class: CellBoard.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) CellBoard.java

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) CellBoard

clean:
	$(RM) $(CLASSDIR)/*.class
