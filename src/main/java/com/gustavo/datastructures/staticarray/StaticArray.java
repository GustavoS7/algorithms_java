package com.gustavo.datastructures.staticarray;

import java.util.Iterator;

public class StaticArray implements Iterable<Integer> {

  private static final int DEFAULT_CAP = 1 << 3; // Shift - 1 << 3 = 2^3 = 8

  public int[] arr;
  private int size = 0;
  private int capacity = 0;

  // Iniciar o array com capacidade default
  public StaticArray() {
    this(DEFAULT_CAP);
  }

  // Iniciar o array com uma determinada capacidade
  public StaticArray(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    this.capacity = capacity;
    arr = new int[capacity];
  }

  // Retorna o tamanho do array
  public int size() {
    return size;
  }

  // Para obter/definir valores sem sobrecarga de chamada de método, você pode usar
  // array_obj.arr[index] e ganhar cerca de 10 vezes mais velocidade!
  public int get(int index) {
    if (index >= 0 && index < size) {
      return arr[index];
    } else {
      throw new IndexOutOfBoundsException("Index out of range.");
    }
  }

  public void set(int index, int elem) {
    if (index >= 0 && index < size) {
      arr[index] = elem;
    } else {
      throw new IndexOutOfBoundsException("Index out of range.");
    }
  }

  // Adicionar um elemento no array
  public void add(int elem) {
    if (size < capacity) {
      arr[size] = elem;
      size++;
    } else {
      System.out.println("Array is full. Cannot add more elements.");
    }
  }

  // Remove o elemento em um índice específico
  // Método com O(n), já que reconstroi o array
  public void removeAt(int rm_index) {
    System.arraycopy(arr, rm_index + 1, arr, rm_index, size - rm_index - 1);
    --size;
  }

  // Pesquisa e remove um elemento se encontrado
  // Método com O(n), já que percorre todo o array para buscar elemento
  public boolean remove(int elem) {
    for (int i = 0; i < size; i++) {
      if (arr[i] == elem) {
        removeAt(i);
        return true;
      }
    }
    return false;
  }

  // Reverte os conteúdos do array
  public void reverse() {
    for (int i = 0; i < size / 2; i++) {
      int tmp = arr[i];
      arr[i] = arr[size - i - 1];
      arr[size - i - 1] = tmp;
    }
  }

  // Executa binary search no array,
  // Método com O(log(n))
  // Array precisa estar ordenado
  // Retorna valor < 0 se não possui o elemento
  public int binarySearch(int key) {
    int index = java.util.Arrays.binarySearch(arr, 0, size, key);
    if (index < 0) index = -index - 1; // Se não encontrado irá dizer onde deverá ser inserido
    return index;
  }

  // Ordena o array
  public void sort() {
    java.util.Arrays.sort(arr, 0, size);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new java.util.Iterator<Integer>() {
      int index = 0;

      public boolean hasNext() {
        return index < size;
      }

      public Integer next() {
        return arr[index++];
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public String toString() {
    if (size == 0) return "[]";
    else {
      StringBuilder sb = new StringBuilder(size).append("[");
      for (int i = 0; i < size - 1; i++) sb.append(arr[i] + ", ");
      return sb.append(arr[size - 1] + "]").toString();
    }
  }

  // Exemplo de uso
  public static void main(String[] args) {
    StaticArray ar = new StaticArray(4);
    ar.add(3);
    ar.add(7);
    ar.add(6);
    ar.add(-2);
    ar.add(5); // Não adicionar

    ar.sort();

    for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

    System.out.println(ar);
  }
}

