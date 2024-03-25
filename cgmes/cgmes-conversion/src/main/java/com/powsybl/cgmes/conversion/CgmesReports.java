/**
 * Copyright (c) 2022, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * SPDX-License-Identifier: MPL-2.0
 */
package com.powsybl.cgmes.conversion;

import com.powsybl.commons.report.ReportNode;
import com.powsybl.commons.report.TypedValue;
import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.Substation;

/**
 * @author Miora Vedelago {@literal <miora.ralambotiana at rte-france.com>}
 */
public final class CgmesReports {

    private CgmesReports() {
    }

    // INFO
    public static void importedCgmesNetworkReport(ReportNode reportNode, String networkId) {
        reportNode.newReportNode()
                .withMessageTemplate("importedCgmesNetwork", "CGMES network ${networkId} is imported.")
                .withUntypedValue("networkId", networkId)
                .withSeverity(TypedValue.INFO_SEVERITY)
                .add();
    }

    // WARN
    public static void badVoltageTargetValueRegulatingControlReport(ReportNode reportNode, String eqId, double targetValue) {
        reportNode.newReportNode()
                .withMessageTemplate("badVoltageTargetValueRegulatingControl", "Equipment ${equipmentId} has a regulating control with bad target value for voltage: ${targetValue}")
                .withUntypedValue("equipmentId", eqId)
                .withTypedValue("targetValue", targetValue, TypedValue.VOLTAGE)
                .withSeverity(TypedValue.WARN_SEVERITY)
                .add();
    }

    public static void badTargetDeadbandRegulatingControlReport(ReportNode reportNode, String eqId, double targetDeadband) {
        reportNode.newReportNode()
                .withMessageTemplate("badTargetDeadbandRegulatingControl", "Equipment ${equipmentId} has a regulating control with bad target deadband: ${targetDeadband}")
                .withUntypedValue("equipmentId", eqId)
                .withTypedValue("targetDeadband", targetDeadband, TypedValue.VOLTAGE)
                .withSeverity(TypedValue.WARN_SEVERITY)
                .add();
    }

    public static void invalidAngleVoltageBusReport(ReportNode reportNode, Bus bus, String nodeId, double v, double angle) {
        reportNode.newReportNode()
                .withMessageTemplate("invalidAngleVoltageBus", "Node ${nodeId} in substation ${substation}, voltageLevel ${voltageLevel}, bus ${bus} has invalid value for voltage and/or angle. Voltage magnitude is ${voltage}, angle is ${angle}")
                .withUntypedValue("substation", bus.getVoltageLevel().getSubstation().map(Substation::getNameOrId).orElse("unknown"))
                .withUntypedValue("voltageLevel", bus.getVoltageLevel().getNameOrId())
                .withUntypedValue("bus", bus.getId())
                .withUntypedValue("nodeId", nodeId)
                .withTypedValue("voltage", v, TypedValue.VOLTAGE)
                .withTypedValue("angle", angle, TypedValue.ANGLE)
                .withSeverity(TypedValue.WARN_SEVERITY)
                .add();
    }

    public static void invalidAngleVoltageNodeReport(ReportNode reportNode, String nodeId, double v, double angle) {
        reportNode.newReportNode()
                .withMessageTemplate("invalidAngleVoltageNode", "Node ${nodeId} has invalid value for voltage and/or angle. Voltage magnitude is ${voltage}, angle is ${angle}")
                .withUntypedValue("nodeId", nodeId)
                .withTypedValue("voltage", v, TypedValue.VOLTAGE)
                .withTypedValue("angle", angle, TypedValue.ANGLE)
                .withSeverity(TypedValue.WARN_SEVERITY)
                .add();
    }

    // ERROR
    public static void inconsistentProfilesTPRequiredReport(ReportNode reportNode, String networkId) {
        reportNode.newReportNode()
                .withMessageTemplate("inconsistentProfilesTPRequired", "Network contains node/breaker ${networkId} information. References to Topological Nodes in SSH/SV files will not be valid if TP is not exported.")
                .withUntypedValue("networkId", networkId)
                .withSeverity(TypedValue.ERROR_SEVERITY)
                .add();
    }

    public static void danglingLineDisconnectedAtBoundaryHasBeenDisconnectedReport(ReportNode reportNode, String danglingLineId) {
        reportNode.newReportNode()
                .withMessageTemplate("danglingLineDisconnectedAtBoundaryHasBeenDisconnected", "DanglingLine ${danglingLineId} was connected at network side and disconnected at boundary side. It has been disconnected also at network side.")
                .withUntypedValue("danglingLineId", danglingLineId)
                .withSeverity(TypedValue.WARN_SEVERITY)
                .add();
    }

    public static void multipleUnpairedDanglingLinesAtSameBoundaryReport(ReportNode reportNode, String danglingLineId, double p0, double q0, double p0Adjusted, double q0Adjusted) {
        reportNode.newReportNode()
                .withMessageTemplate("multipleUnpairedDanglingLinesAtSameBoundary", "Multiple unpaired DanglingLines were connected at the same boundary side. Adjusted original injection from (${p0}, ${q0}) to (${p0Adjusted}, ${q0Adjusted}) for dangling line ${danglingLineId}.")
                .withUntypedValue("danglingLineId", danglingLineId)
                .withUntypedValue("p0", p0)
                .withUntypedValue("q0", q0)
                .withUntypedValue("p0Adjusted", p0Adjusted)
                .withUntypedValue("q0Adjusted", q0Adjusted)
                .withSeverity(TypedValue.WARN_SEVERITY)
                .add();
    }
}
