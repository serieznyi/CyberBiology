all: clean class jar

class:
	@echo "$@"
	mkdir -p ./build/
	cd ./src/main/java && javac -Xlint:unchecked -d ../../../build/ org/cyberbiology/MainWindow.java

jar:
	@echo "$@"
	@cd ./build/ && jar -cvmf ../META-INF/* world.jar $$(find . -type f -name "*.class" | xargs echo)

clean:
	@echo "$@"
	find . -type f -name *.class -exec rm -rf {} \;
	rm -rf ./build
