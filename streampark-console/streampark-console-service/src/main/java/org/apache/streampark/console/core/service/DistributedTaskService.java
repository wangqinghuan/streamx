/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.streampark.console.core.service;

import org.apache.streampark.console.base.mybatis.entity.BaseEntity;
import org.apache.streampark.console.core.entity.DistributedTask;
import org.apache.streampark.console.core.enums.DistributedTaskEnum;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * DistributedTaskService is the interface for managing tasks.
 */
public interface DistributedTaskService extends IService<DistributedTask> {

    /**
     * Initialize the consistent hash ring.
     * @param allServers All servers
     * @param serverId The name of the current server
     */
    void init(Set<String> allServers, String serverId);

    /**
     * This interface is responsible for polling the database to retrieve task records and execute the corresponding operations.
     * @param distributedTask distributedTask
     */
    void executeDistributedTask(DistributedTask distributedTask) throws Exception;

    /**
     * This interface handles task redistribution when server nodes are added.
     * @param serverId String
     */
    void addServer(String serverId);

    /**
     * This interface handles task redistribution when server nodes are removed.
     * @param serverId String
     */
    void removeServer(String serverId);

    /**
     * Determine whether the task is processed locally.
     *
     * @param appId Long
     * @return boolean
     */
    boolean isLocalProcessing(Long appId);

    /**
     * Save Distributed Task.
     *
     * @param appParam It may be one of the following values: FlinkApplication, SparkApplication
     * @param autoStart boolean
     * @param action It may be one of the following values: START, RESTART, REVOKE, CANCEL, ABORT
     */
    void saveDistributedTask(BaseEntity appParam, boolean autoStart, DistributedTaskEnum action);
}
