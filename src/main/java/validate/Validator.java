/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

public abstract class Validator<T> {
    private Validator<T> next;

    public Validator<T> setNext(Validator<T> next) {
        this.next = next;
        return next;
    }
    public abstract void validate(T item) throws ValidationException;
    protected void callNext(T item) throws ValidationException {
        if (next != null) {
            next.validate(item);
        }
    }
}
