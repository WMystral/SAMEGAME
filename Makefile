#author MAUMENE Juliette JORANDON Arnaud 

JFLAGS = -g
JC = javac
J = java Main
.SUFFIXES: .java .class
.java.class:
			$(JC) $(JFLAGS) $*.java

CLASSES = \
		OnClick.java \
		Fenetre.java \
		Decalage.java \
		Grid.java \
		Check.java \
		Main.java \


default : classes

classes: $(CLASSES:.java=.class)

clean: 
	$(RM) *.class

test:
	$(J)
