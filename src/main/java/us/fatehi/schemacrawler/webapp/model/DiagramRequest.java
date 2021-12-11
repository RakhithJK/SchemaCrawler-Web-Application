/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2021, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/
package us.fatehi.schemacrawler.webapp.model;

import static java.util.Objects.requireNonNull;
import static us.fatehi.utility.Utility.isBlank;

import java.io.Reader;
import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DiagramRequest implements Serializable {

  private static final long serialVersionUID = 2065519510282344200L;

  /**
   * Factory method to deserialize a JSON request.
   *
   * @param jsonReader JSON serialized request reader.
   * @return Deserialized Java request.
   */
  public static DiagramRequest fromJson(final Reader jsonReader) {
    requireNonNull(jsonReader, "No reader provided");
    return DiagramRequestUtility.readDiagramRequest(jsonReader);
  }

  private final DiagramKey key;
  private final Instant timestamp;
  private String error;

  @NotNull
  @Size(min = 2, message = "Please enter your full name")
  private String name;

  @NotNull
  @Pattern(
      regexp =
          "\\A[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z",
      message = "Please enter a valid email address")
  private String email;

  @NotNull
  @Size(min = 1, message = "Please select a file to upload")
  private String file;

  private String title;

  /** Public constructor. Generates a random key, and sets the creation timestamp. */
  public DiagramRequest() {
    timestamp = Instant.now();
    key = new DiagramKey();
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  /**
   * Returns the email address of the requester.
   *
   * @return Email address of the requester.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns the exception message.
   *
   * @return Error message
   */
  public String getError() {
    return error;
  }

  /**
   * Returns the uploaded file name.
   *
   * @return Uploaded file name.
   */
  public String getFile() {
    return file;
  }

  /**
   * Returns a randomized unique key for the request.
   *
   * @return Unique key for the request.
   */
  public DiagramKey getKey() {
    return key;
  }

  /**
   * Returns the name of the requester.
   *
   * @return Name of the requester.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the request creation timestamp.
   *
   * @return Request creation timestamp.
   */
  public Instant getTimestamp() {
    return timestamp;
  }

  public String getTitle() {
    return title;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  public boolean hasLogMessage() {
    return !isBlank(error);
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Set error message.
   *
   * @param Error message
   */
  public void setError(final String error) {
    this.error = error;
  }

  /**
   * Sets the uploaded file name.
   *
   * @param file Uploaded file name.
   */
  public void setFile(final String file) {
    this.file = file;
  }

  /**
   * Sets the name of the requester.
   *
   * @param name Name of the requester.
   */
  public void setName(final String name) {
    this.name = name;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  /**
   * Converts this object to JSON.
   *
   * @return JSON string
   */
  public String toJson() {
    return DiagramRequestUtility.diagramRequestToJson(this);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return toJson();
  }
}
