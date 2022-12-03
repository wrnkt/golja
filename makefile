JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: BoardLoader.class BoardManager.class Appliable.class Cell.class

rebuild:
	make clean
	make

Appliable.class: Appliable.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Appliable.java

BoardLoader.class: BoardLoader.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) BoardLoader.java

BoardManager.class: BoardManager.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) BoardManager.java

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) BoardManager

clean:
	$(RM) $(CLASSDIR)/*.class
