import subprocess

def check_version(command):
    result = subprocess.run(command, capture_output=True, text=True, shell=True)
    print(result.stdout)

print("Java version:")
check_version("java -version")
print("Javac version:")
check_version("javac -version")
print("JavaFX version (if available):")
check_version("java -jar path/to/javafx-jmods.jar")  # Substitua pelo caminho correto se tiver o JAR do JavaFX
