package demo.LookupMethodInjection;

public abstract class HelloApiImpl implements HelloApi{
	private Printer printer;
    
    @Override
    public void sayHello() {
        printer.print("setter");
        createPrototypePrinter().print("prototype");
        createSingletonPrinter().print("singleton");
    }
    
    public abstract PrinterAlt createPrototypePrinter();
        
    public Printer createSingletonPrinter() {
        System.out.println("This method wont be invoked!");
        return new Printer();
    }
    
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

}
