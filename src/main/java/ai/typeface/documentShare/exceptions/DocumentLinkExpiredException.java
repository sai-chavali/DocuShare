package ai.typeface.documentShare.exceptions;

public class DocumentLinkExpiredException extends RuntimeException{
    public DocumentLinkExpiredException(){
        super("This link got expired. Please contact owner for more details");
    }
}
