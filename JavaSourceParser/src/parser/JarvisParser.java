package parser;

import jarvis.graph.visualiser.source.INode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class JarvisParser {

	private String path;
	private List<INode> nodeConnectedGraph = new ArrayList<INode>();
	private static ArrayList<JarvisClass> classes = new ArrayList<JarvisClass>();

	public JarvisParser(String path) {
		this.path = path;

		try {
			parseDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void parseDirectory() throws IOException {
		File dirs = new File(path);

		String dirPath = dirs.getCanonicalPath();
		File root = new File(dirPath);
		File[] files = root.listFiles();

		
		if (!root.getName().contains("src")) {
			//u slucaju da je data putanja samo do projekta prolazi se kroz projekat i traze se src folderi.
			ArrayList<File> sources = new ArrayList<File>();
			for (File f : files) {
				if (f.getName().contains("src")) {
					sources.add(f);
				}
			}

			File[] srcFiles = new File[sources.size()];
			for (int i = 0; i < srcFiles.length; i++) {
				srcFiles[i] = sources.get(i);
			}
			readFiles(srcFiles);
		}else{
			//u slucaju da je u dijalogu odabran src folder, parsiram samo njegove podelemente.
			readFiles(files);
		}

	}

	public void readFiles(File[] files) throws IOException {
		for (File file : files) {
			if (file.isDirectory()) {
				readFiles(file.listFiles());
			} else {
				parseCode(readCode(file.getAbsolutePath()));
			}
		}
	}
	public void parseCode(String code) throws IOException {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(code.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		JarvisClass jarvisClass = new JarvisClass();

		List<ImportDeclaration> imports = cu.imports();
		for (ImportDeclaration i : imports) {
			JarvisImport jarvisImport = new JarvisImport();
			jarvisImport.setName(i.toString().substring("import".length(), i.toString().length()).trim());
			jarvisClass.getImports().add(jarvisImport);
		}

		List<AbstractTypeDeclaration> types = cu.types();

		for (AbstractTypeDeclaration type : types) {
			// System.out.println("type: " + type.getName());
			String typeCode = type.toString();
			findSuperclass(jarvisClass, typeCode);

			String interfaceString = type.toString();
			findInterfaces(jarvisClass, interfaceString);

			if (type.getNodeType() == ASTNode.TYPE_DECLARATION) {

				jarvisClass.setName(type.getName().getFullyQualifiedName());
				List<BodyDeclaration> bodies = type.bodyDeclarations();
				for (BodyDeclaration body : bodies) {

					if (body.getNodeType() == ASTNode.METHOD_DECLARATION) {
						MethodDeclaration method = (MethodDeclaration) body;

						JarvisMethod jarvisMethod = new JarvisMethod();
						JarvisConstructor jarvisConstructor = new JarvisConstructor();
						String name = method.getName().getFullyQualifiedName();
						List parameters = method.parameters();

						if (method.isConstructor()) {
							jarvisConstructor.setName(name);
							for (int i = 0; i < parameters.size(); i++) {
								jarvisConstructor.getParameters().add(parameters.get(i).toString());
							}

							jarvisClass.getConstructors().add(jarvisConstructor);
						} else {
							jarvisMethod.setName(name);
							for (int i = 0; i < parameters.size(); i++) {
								jarvisMethod.getParameters().add(parameters.get(i).toString());
							}

							jarvisClass.getMethods().add(jarvisMethod);
						}

					}

					if (body.getNodeType() == ASTNode.FIELD_DECLARATION) {
						FieldDeclaration field = (FieldDeclaration) body;

						JarvisField jarvisField = new JarvisField();
						jarvisField.setModifier(readModifier(field.getModifiers()));

						String fieldName = field.fragments().get(0).toString();

						fieldNameAndValue(jarvisField, fieldName);

						jarvisField.setType(field.getType().toString());
						jarvisClass.getFields().add(jarvisField);

					}

				}

				if (!classes.contains(jarvisClass))
					classes.add(jarvisClass);
			}
		}

	}

	public String readModifier(int mod) {
		if (mod == Modifier.PRIVATE)
			return "private";
		else if (mod == Modifier.PROTECTED)
			return "protected";
		else if (mod == Modifier.PUBLIC)
			return "protected";
		else if (mod == Modifier.STATIC) {
			System.out.println("static mod: " + mod);
			return "static";
		} else
			return "";

	}

	private void findInterfaces(JarvisClass jarvisClass, String interfaceString) {
		if (interfaceString.contains("public class") && interfaceString.contains("implements")) {

			String interfaceBegin = interfaceString.substring(interfaceString.indexOf("implements"));
			if (interfaceBegin.contains("{")) {
				interfaceBegin = interfaceBegin.substring("implements".length(), interfaceBegin.indexOf("{")).trim();
				StringTokenizer tokenizer = new StringTokenizer(interfaceBegin, ",");
			
				while (tokenizer.hasMoreElements()) {
					jarvisClass.getInterfaces().add(tokenizer.nextToken());
				}
			}
		}
	}

	private void findSuperclass(JarvisClass jarvisClass, String typeCode) {
		if (typeCode.contains("public class")) {
			typeCode = typeCode.substring(typeCode.indexOf("public class"));
			if (typeCode.contains("extends")) {
				int index = typeCode.indexOf("extends");
				String cutted = typeCode.substring(index);

				int indexOfName;
				if (cutted.contains("implements")) {
					indexOfName = cutted.indexOf("implements");
				} else {
					indexOfName = cutted.indexOf("{");
				}

				String superclass = cutted.substring(0, indexOfName);

				if (superclass.contains("{")) {
					int end = superclass.indexOf("{");
					superclass = superclass.substring(0, end);
				}
				superclass = superclass.substring("extends".length(), superclass.length()).trim();
				jarvisClass.setSuperclass(superclass);
			}
		}
	}

	public String readCode(String path) throws IOException {
		BufferedReader br;
		StringBuilder sb = null;

		br = new BufferedReader(new FileReader(path));

		sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append("\n");
			line = br.readLine();
		}

		br.close();
		return sb.toString();
	}

	public List<INode> getNodeConnectedGraph() {

		connectByReference();

		for (JarvisClass jarvisClass : classes) {
			nodeConnectedGraph.add(jarvisClass);
		}

		return nodeConnectedGraph;
	}

	private static void connectByReference() {
		for (JarvisClass jarvisClass : classes) {
			ArrayList<JarvisField> fields = jarvisClass.getFields();
			
			for (JarvisField field : fields) {
				for (JarvisClass referencedClass : classes) {
					if (field.getType().equals(referencedClass.getName())) {
						if(!jarvisClass.getReferences().contains(referencedClass))
							jarvisClass.getReferences().add(referencedClass);
					}

				}
			}
			

		}
	}

	private void fieldNameAndValue(JarvisField jarvisField, String fieldName) {
		if (fieldName.contains("=")) {
			int index = fieldName.indexOf("=");
			String name = fieldName.substring(0, index).trim();
			String value = fieldName.substring(++index, fieldName.length()).trim();
			jarvisField.setName(name);
			jarvisField.setValue(value);
		} else {
			jarvisField.setName(fieldName);
		}
	}

}
