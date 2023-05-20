package ai.typeface.documentShare.exceptions;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(Long id){
        super(String.format("The document with id %d is not found", id));
    }
}
