package com.gustavo.datastructures.doublylinkedlist;

public class DoublyLinkedList<T> implements Iterable<T> {

  private int size = 0;
  private Node<T> head = null;
  private Node<T> tail = null;

  // Classe interna Node para representação dos dados
  private static class Node<T> {
    private T data;
    private Node<T> prev, next;

    public Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    @Override 
    public String toString() {
      return data.toString();
    }
  }

  // Limpar Linked List, O(n)
  public void clear() {
    Node<T> trav = head;
    while (trav != null) {
      Node<T> next = trav.next;
      trav.prev = trav.next = null;
      trav.data = null;
      trav = next;
    }
    head = tail = trav = null;
    size = 0;
  }

  // Retorna o tamanho da Linked List
  public int size() {
    return size;
  }

  // Retorna se a lista está vazio
  public boolean isEmpty() {
    return size() == 0;
  }

  // Adiciona um elemento ao tail da Lista, O(1)
  public void add(T elem) {
    addLast(elem);
  }

  // Adiciona um node ao tail da lista, O(1)
  public void addLast(T elem) {
    if (isEmpty()) {
      head = tail = new Node<T>(elem, null, null);
    } else {
      tail.next = new Node<T>(elem, tail, null);
      tail = tail.next;
    }
    size++;
  }

  // Adiciona node ao head ta lista, O(1)
  public void addFirst(T elem) {
    if (isEmpty()) {
      head = tail = new Node<T>(elem, null, null);
    } else {
      head.prev = new Node<T>(elem, null, head);
      head = head.prev;
    }
    size++;
  }

  // Adiciona um elemento em um índice específico
  public void addAt(int index, T data) throws Exception {
    if (index < 0 || index > size) {
      throw new Exception("Illegal Index");
    }
    if (index == 0) {
      addFirst(data);
      return;
    }
    if (index == size) {
      addLast(data);
      return;
    }

    Node<T> temp = head;
    for (int i = 0; i < index - 1; i++) {
      temp = temp.next;
    }
    Node<T> newNode = new Node<T>(data, temp, temp.next);
    temp.next.prev = newNode;
    temp.next = newNode;

    size++;
  }

  // Verifica o valor do primeiro node se existir, O(1)
  public T peekFirst() {
    if (isEmpty()) throw new RuntimeException("Empty list");
    return head.data;
  }

  // Verifica o valor do último node se existir, O(1)
  public T peekLast() {
    if (isEmpty()) throw new RuntimeException("Empty list");
    return tail.data;
  }

  // Remove o primeiro valor da lista, O(1)
  public T removeFirst() {
    if (isEmpty()) throw new RuntimeException("Empty list");

    T data = head.data;
    head = head.next;
    --size;

    if (isEmpty()) tail = null;
    else head.prev = null;

    return data;
  }

  // Remove o último valor da lista, O(1)
  public T removeLast() {
    if (isEmpty()) throw new RuntimeException("Empty list");

    T data = tail.data;
    tail = tail.prev;
    --size;

    if (isEmpty()) head = null;
    else tail.next = null;

    return data;
  }

  // Remove um node arbitrário da lista, O(1)
  private T remove(Node<T> node) {
    if (node.prev == null) return removeFirst();
    if (node.next == null) return removeLast();

    node.next.prev = node.prev;
    node.prev.next = node.next;

    T data = node.data;

    node.data = null;
    node = node.prev = node.next = null;

    --size;

    return data;
  }

  // Remove node em um índice particular, O(n)
  public T removeAt(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException();
    }

    int i;
    Node<T> trav;

    if (index < size / 2) {
      for (i = 0, trav = head; i != index; i++) {
        trav = trav.next;
      }
    } else {
      for (i = size - 1, trav = tail; i != index; i--) {
        trav = trav.prev;
      }
    }
    return remove(trav);
  }

  // Remove um valor particular na lista, O(n)
  public boolean remove(Object obj) {
    Node<T> trav = head;

    if (obj == null) {
      for(trav = head; trav != null; trav = trav.next) {
        if (trav.data == null) {
          remove(trav);
          return true;
        }
      }
    } else {
      for (trav = head; trav != null; trav = trav.next) {
        if (obj.equals(trav.data)) {
          remove(trav);
          return true;
        }
      }
    }
    return false;
  }

  // Encontra o índice de um valor particular na lista, O(n)
  public int indexOf(Object obj) {
    int index = 0;
    Node<T> trav = head;

    if (obj == null) {
      for (; trav != null; trav = trav.next, index++) {
        if (trav.data == null) {
          return index;
        }
      }
    } else {
      for (; trav != null; trav = trav.next, index++) {
        if (obj.equals(trav.data)) {
          return index;
        }
      }
    }
    return -1;
  }

  // Verifica se um valor está contido na lista
  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

  @Override
  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
      private Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav != null;
      }

      @Override
      public T next() {
        T data = trav.data;
        trav = trav.next;
        return data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    Node<T> trav = head;
    while (trav != null) {
      sb.append(trav.data);
      if (trav.next != null) {
        sb.append(", ");
      }
      trav = trav.next;
    }
    sb.append(" ]");
    return sb.toString();
  }

  // Exemplo de uso
  public static void main(String[] args) {
    DoublyLinkedList<Integer> ar = new DoublyLinkedList<Integer>();
    ar.add(3);
    ar.add(7);
    ar.add(6);
    ar.add(-2);
    ar.add(5); // Não adicionar

    System.out.println(ar);

    ar.removeAt(2);

    System.out.println(ar);
  }
}
