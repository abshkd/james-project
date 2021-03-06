/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package org.apache.james.server.core;

import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import javax.mail.MessagingException;
import org.apache.mailet.base.RFC2822Headers;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MailHeadersTest {

    @Test
    public void testHeadersOrder() throws MessagingException {
        MailHeaders header = new MailHeaders(new ByteArrayInputStream((RFC2822Headers.SUBJECT + ": testsubject\r\n").
                getBytes()));
        header.setHeader(RFC2822Headers.RETURN_PATH, "<test@test>");
        header.setHeader(RFC2822Headers.FROM, "<test2@test.de>");
        @SuppressWarnings("unchecked")
        Enumeration<String> h = header.getAllHeaderLines();

        assertEquals(h.nextElement(), "Return-Path: <test@test>");
        assertEquals(h.nextElement(), "From: <test2@test.de>");
        assertEquals(h.nextElement(), "Subject: testsubject");
    }
}
