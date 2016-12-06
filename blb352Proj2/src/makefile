GS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	FaultHandlerThread.java \
	Frame.java \
	Memory.java \
	MemoryManagerThread.java \
	SwapFrameValue.java \
	UserProcessThread.java \
	VMsim.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
