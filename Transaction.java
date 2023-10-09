public class Transaction {
    String from;
    String note;

    Transaction(String from, String note){
        this.from = from;
        this.note = note;
    }

    @Override
    public String toString(){
        return from + " " + note;
    }
}
