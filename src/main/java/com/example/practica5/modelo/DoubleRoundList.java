package com.example.practica5.modelo;

import java.util.Comparator;

public class DoubleRoundList<T> {
    private DoubleNode begin;
    private DoubleNode end;

    public DoubleRoundList(){
        this.begin = null;
        this.end = null;
    }

    public void insertBeginning(T data){
        DoubleNode n = new DoubleNode(data);
        if(begin == null){
            begin = n;
            end = n;
            n.setNext(begin);
        }else{
            n.setNext(begin);
            begin = n;
            end.setNext(begin);
        }

    }

    public void insertEnd(T data){
        if(begin == null){
            insertBeginning(data);
        }else {
            DoubleNode n = new DoubleNode(data);
            end.setNext(n);
            begin.setPrev(n);
            n.setPrev(end);
            n.setNext(begin);
            end = n;
        }
    }

    public String printAll(){
        DoubleNode r = begin;
        StringBuilder string = new StringBuilder();
        do{
            string.append(r.getData()).append("\n");
            r = r.getNext();
        }while(r.getPrev() != end);
        return string.toString();
    }

    public T removeFirst(){
        T removableData;
        if(begin == end){
            removableData = (T) begin.getData();
            begin = null;
            end = null;
            return removableData;
        }
        removableData = (T) begin.getData();
        end.setNext(begin.getNext());
        begin.getNext().setPrev(end);
        begin = begin.getNext();
        return removableData;

    }

    public T removeLast(){
        T removableData;
        if(begin == end) return removeFirst();
        removableData = (T) end.getData();
        begin.setPrev(end.getPrev());
        end.getPrev().setNext(begin);
        end = end.getPrev();
        return removableData;
    }

    private String printRecursively(DoubleNode n){
        if(n == null) return "Empty list";
        if(n.getNext() == begin) return String.valueOf(n.getData());
        return String.valueOf(n.getData()) + "\n" + printRecursively(n.getNext());
    }

    public String printRecursively(){
        return printRecursively(begin);
    }

    public T remove(T data){
        DoubleNode r = begin;
        if(r.getData() == data) return removeFirst();
        do{
            r = r.getNext();
            if(r.getData() == data){
                if(r.getNext() == begin) return removeLast();
                r.getNext().setPrev(r.getPrev());
                r.getPrev().setNext(r.getNext());
                return data;
            }
        }while(r.getPrev() != end);
        return null;
    }

    private int search(T data, DoubleNode n, int count){
        if(n.getNext() == begin && n.getData() != data) return -1;
        if(n.getData() == data && n == begin) return 0;
        if(n.getData() == data) return count;
        return search(data, n.getNext(), count+1);
    }

    public int search(T data){
        return search(data, begin, 0);
    }

    public T remove(int pos){
        DoubleNode r = begin;
        int count = 0;
        if(pos == 0) return removeFirst();
        do{
            count++;
            r = r.getNext();
            if(count == pos){
                if(r.getNext() == begin) return removeLast();
                r.getNext().setPrev(r.getPrev());
                r.getPrev().setNext(r.getNext());
                return (T) r.getData();
            }
        }while(r.getPrev() != end);
        return null;
    }

    public void sort(Comparator<T> comparador){
        if (begin == null || begin == end) return;
        DoubleNode r = begin;
        T aux;
        boolean movementMade = false;
        do{
            movementMade = false;
            do{
                if(comparador.compare((T) r.getNext().getData(), (T) r.getData()) < 0){
                    aux = (T) r.getData();
                    r.setData(r.getNext().getData());
                    r.getNext().setData(aux);
                    movementMade = true;
                }
                r = r.getNext();
            }while(r != end);
            r = begin;
        }while(movementMade);
    }

    public void removePairs(){
        DoubleNode r = begin;
        do{
            if((int) r.getData() % 2 == 0){
                if(r == begin) {
                    removeFirst();
                }else if(r == end) {
                    removeLast();
                }else{
                    r.getNext().setPrev(r.getPrev());
                    r.getPrev().setNext(r.getNext());
                }
            }
            r = r.getNext();
        }while (r != begin);
    }

    public int getSize() {
        if (begin == null) return 0;
        int count = 0;
        DoubleNode r = begin;
        do {
            count++;
            r = r.getNext();
        } while (r != begin);
        return count;
    }

    public T get(int pos) {
        if (begin == null || pos < 0) return null;
        DoubleNode r = begin;
        int count = 0;
        do {
            if (count == pos) return (T) r.getData();
            count++;
            r = r.getNext();
        } while (r != begin);
        return null;
    }
}
