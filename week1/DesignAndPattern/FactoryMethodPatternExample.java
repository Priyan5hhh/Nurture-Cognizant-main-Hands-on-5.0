public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        DocumentFactory factory = new PdfFactory();
        Document pdf = factory.creaDocument();
         pdf.open();

        DocumentFactory factory1 = new WordFactory();
        Document word = factory1.creaDocument();
        word.open();

        DocumentFactory factory2 = new ExcelFactory();
        Document excel = factory2.creaDocument();
        excel.open();
    }
}

interface Document {
    void open();   
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Opening word document");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening pdf file");
    }    
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel file");
    }
}

abstract class DocumentFactory {
    abstract Document creaDocument();
}

class WordFactory extends DocumentFactory {
    Document creaDocument () {
        return new WordDocument();
    }
}

class PdfFactory extends DocumentFactory {
    Document creaDocument () {
        return new PdfDocument();
    }
}

class ExcelFactory extends DocumentFactory {
    Document creaDocument () {
        return new ExcelDocument();
    }
}

