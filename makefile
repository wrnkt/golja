JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: BoardLoader.class

rebuild:
	make clean
	make

BoardLoader.class: BoardLoader.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) BoardLoader.java

BoardManager.class: BoardManager.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) BoardManager.java

Cell.class: Cell.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) Cell.java

run:
	java -cp $(CLASSDIR) BoardLoader

clean:
	$(RM) $(CLASSDIR)/*.class
