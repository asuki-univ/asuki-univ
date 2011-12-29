package adt.datastruct;

class SinglyLinkedListNode<T> {
    T value;
    SinglyLinkedListNode<T> next;
}

class SinglyLinkedList<T> {
    SinglyLinkedListNode<T> first;
}

class DoublyLinkedListNode<T> {
    T value;
    DoublyLinkedListNode<T> prev;
    DoublyLinkedListNode<T> next;
}

class DoublyLinkedList<T> {
    DoublyLinkedListNode<T> first;
    DoublyLinkedListNode<T> last;
}