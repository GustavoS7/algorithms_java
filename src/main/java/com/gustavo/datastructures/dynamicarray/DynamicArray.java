package com.gustavo.datastructures.dynamicarray;

import java.util.Iterator;

public class DynamicArray implements Iterable<Integer> {

  private static final int DEFAULT_CAP = 1 << 3; // Shift - 1 << 3 = 2^3 = 8

  public int[] arr;
  private int len = 0;
  private int capacity = 0;

  // Iniciar o array com capacidade default
  public DynamicArray() {
    this(DEFAULT_CAP);
  }

  // Iniciar o array com uma determinada capacidade
  public DynamicArray(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    this.capacity = capacity;
    arr = new int[capacity];
  }

  // Dado um array transforma em um dynamic array
  public DynamicArray(int[] array) {
    if (array == null) throw new IllegalArgumentException("Array cannot be null");
    arr = java.util.Arrays.copyOf(array, array.length);
    capacity = len = array.length;
  }

  // Retorna o tamanho do array
  public int size() {
    return len;
  }

  // Retorna true/false caso o array esteja vazio
  public boolean isEmpty() {
    return len == 0;
  }

  // Para obter/definir valores sem sobrecarga de chamada de método, você pode usar
  // array_obj.arr[index] e ganhar cerca de 10 vezes mais velocidade!
  public int get(int index) {
    if (index >= 0 && index < len) {
      return arr[index];
    } else {
      throw new IndexOutOfBoundsException("Index out of range.");
    }
  }

  public void set(int index, int elem) {
    if (index >= 0 && index < len) {
      arr[index] = elem;
    } else {
      throw new IndexOutOfBoundsException("Index out of range.");
    }
  }

  // Adicionar um elemento no array
  public void add(int elem) {
    if (len + 1 >= capacity) {
      if (capacity == 0) capacity = 1;
      else capacity *= 2; // Dobrar o tamanho
      arr = java.util.Arrays.copyOf(arr, capacity);
    }
    arr[len++] = elem;
  }

  // Remove o elemento em um índice específico
  // Método com O(n), já que reconstroi o array
  public void removeAt(int rm_index) {
    System.arraycopy(arr, rm_index + 1, arr, rm_index, len - rm_index - 1);
    --len;
    --capacity;
  }

  // Pesquisa e remove um elemento se encontrado
  // Método com O(n), já que percorre todo o array para buscar elemento
  public boolean remove(int elem) {
    for (int i = 0; i < len; i++) {
      if (arr[i] == elem) {
        removeAt(i);
        return true;
      }
    }
    return false;
  }

  // Reverte os conteúdos do array
  public void reverse() {
    for (int i = 0; i < len / 2; i++) {
      int tmp = arr[i];
      arr[i] = arr[len - i - 1];
      arr[len - i - 1] = tmp;
    }
  }

  // Executa binary search no array,
  // Método com O(log(n))
  // Array precisa estar ordenado
  // Retorna valor < 0 se não possui o elemento
  public int binarySearch(int key) {
    int index = java.util.Arrays.binarySearch(arr, 0, len, key);
    if (index < 0) index = -index - 1; // Se não encontrado irá dizer onde deverá ser inserido
    return index;
  }

  // Ordena o array
  public void sort() {
    java.util.Arrays.sort(arr, 0, len);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new java.util.Iterator<Integer>() {
      int index = 0;

      public boolean hasNext() {
        return index < len;
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
    if (len == 0) return "[]";
    else {
      StringBuilder sb = new StringBuilder(len).append("[");
      for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
      return sb.append(arr[len - 1] + "]").toString();
    }
  }

  // Exemplo de uso
  public static void main(String[] args) {
    DynamicArray ar = new DynamicArray(10);
    ar.add(3);
    ar.add(7);
    ar.add(6);
    ar.add(-2);

    ar.sort();

    for (int i = 0; i < ar.size(); i++) System.out.println(ar.get(i));

    System.out.println(ar);
  }
}
