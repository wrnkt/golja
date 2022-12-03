JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: BoardManager.class

rebuild:
	make clean
	make

BoardManager.class: BoardManager.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) BoardManager.java

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) BoardManager

clean:
	$(RM) $(CLASSDIR)/*.class
