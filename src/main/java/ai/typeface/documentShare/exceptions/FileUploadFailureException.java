package ai.typeface.documentShare.exceptions;

public class FileUploadFailureException extends RuntimeException{
    public FileUploadFailureException(){
        super("Unable to upload file. Please try again!!");
    }
}
