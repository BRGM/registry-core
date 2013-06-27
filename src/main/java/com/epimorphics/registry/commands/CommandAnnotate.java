/******************************************************************
 * File:        CommandAnnotate.java
 * Created by:  Dave Reynolds
 * Created on:  29 Apr 2013
 * 
 * (c) Copyright 2013, Epimorphics Limited
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *****************************************************************/

package com.epimorphics.registry.commands;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import com.epimorphics.registry.core.Command;
import com.epimorphics.registry.core.RegisterItem;
import com.epimorphics.registry.message.Message;
import com.epimorphics.registry.vocab.RegistryVocab;
import com.epimorphics.registry.webapi.Parameters;
import com.epimorphics.util.EpiException;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.sun.jersey.api.NotFoundException;

public class CommandAnnotate extends Command {

    @Override
    public Response doExecute() {
        String graphURI = target + "?annotation=" + parameters.getFirst(Parameters.ANNOTATION);
        store.lock(target);
        try {
            RegisterItem item = store.getItem(target, false);
            if (item == null) {
                throw new NotFoundException();
            }
            store.storeGraph(graphURI, getPayload());
            item.getRoot().addProperty(RegistryVocab.annotation, ResourceFactory.createResource(graphURI));
            store.update(item, false);
            
            // notify event
            Message message = new Message(this);
            message.setMessage( payload );
            notify(message);
            
            try {
                return Response.created(new URI(graphURI)).build();
            } catch (URISyntaxException e) {
                throw new EpiException(e);
            }
        } finally {
            store.unlock(target);
        }
    }

}
