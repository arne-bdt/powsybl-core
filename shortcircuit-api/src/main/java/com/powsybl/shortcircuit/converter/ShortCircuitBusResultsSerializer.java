/**
 * Copyright (c) 2022, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.powsybl.shortcircuit.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.powsybl.commons.json.JsonUtil;
import com.powsybl.shortcircuit.ShortCircuitBusResults;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Thomas Adam <tadam at silicom.fr>
 */
public class ShortCircuitBusResultsSerializer extends StdSerializer<ShortCircuitBusResults> {

    public ShortCircuitBusResultsSerializer() {
        super(ShortCircuitBusResults.class);
    }

    @Override
    public void serialize(ShortCircuitBusResults busResults, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Objects.requireNonNull(busResults);

        jsonGenerator.writeStartObject();
        JsonUtil.writeOptionalStringField(jsonGenerator, "voltageLevelId", busResults.getVoltageLevelId());
        JsonUtil.writeOptionalStringField(jsonGenerator, "busId", busResults.getBusId());
        jsonGenerator.writeObjectField("voltage", busResults.getVoltage());

        jsonGenerator.writeEndObject();
    }
}
