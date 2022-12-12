JC = javac

JCFLAGS = -g
JFLAGS = -Wall

CLASSDIR = classes

default: BoardLoader.class BoardManager.class BoardPrinter.class AppliableRule.class Cell.class

rebuild:
	make clean
	make

BoardPrinter.class: BoardPrinter.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) BoardPrinter.java

AppliableRule.class: AppliableRule.java
	$(JC) $(JCFLAGS) -d $(CLASSDIR) AppliableRule.java

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
