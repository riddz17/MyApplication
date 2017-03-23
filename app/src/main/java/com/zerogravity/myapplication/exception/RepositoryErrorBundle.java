/*
  Copyright (C) 2015 Fernando Cejas Open Source Project

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.zerogravity.myapplication.exception;


import com.zerogravity.myapplication.exception.ErrorBundle;

/**
 * Wrapper around Exceptions used to manage errors in the repository.
 */
class RepositoryErrorBundle implements ErrorBundle {

  private final Exception exception;

  RepositoryErrorBundle(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    String message = "";
    if (this.exception != null) {
      message = this.exception.getMessage();
    }
    return message;
  }
}
