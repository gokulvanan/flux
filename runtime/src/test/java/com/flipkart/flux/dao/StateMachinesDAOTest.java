/*
 * Copyright 2012-2016, the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.flux.dao;

import com.flipkart.flux.dao.iface.StateMachinesDAO;
import com.flipkart.flux.domain.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shyam.akirala
 */
public class StateMachinesDAOTest {

    @Test
    public void createSMTest() {
        StateMachinesDAO stateMachinesDAO = new StateMachinesDAOImpl();
        DummyTask task = new DummyTask();
        DummyOnEntryHook onEntryHook = new DummyOnEntryHook();
        DummyOnExitHook onExitHook = new DummyOnExitHook();
        State<Data> state1 = new State<Data>(2L, "state1", "desc1", onEntryHook, task, onExitHook, 3L, 60L);
        State<Data> state2 = new State<Data>(2L, "state2", "desc2", null, null, null, 2L, 50L);
        List<State<Data>> states = new ArrayList<State<Data>>();
        states.add(state1);
        states.add(state2);
        StateMachine<Data> stateMachine = new StateMachine<Data>(2L, "test_name", "test_desc", states, state1);
        Long savedSMId = stateMachinesDAO.create(stateMachine);

        StateMachine stateMachine1 = stateMachinesDAO.findById(savedSMId);
        Assert.assertNotNull(stateMachine1);
    }
}

/** Event data class*/
class Data {
    String data;

    public Data(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}