package com.gussoft.inventario.core.exception;

public class InsufficientStockException extends RuntimeException {

  public InsufficientStockException(String message) {
    super(message);
  }

}