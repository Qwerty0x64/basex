package org.basex.query.func;

import static org.basex.query.QueryText.*;
import static org.basex.query.util.Flag.*;
import static org.basex.query.value.type.SeqType.*;

import java.util.*;
import java.util.function.Supplier;

import org.basex.query.func.geo.*;
import org.basex.query.func.request.*;
import org.basex.query.func.rest.*;
import org.basex.query.func.session.*;
import org.basex.query.func.sessions.*;
import org.basex.query.func.ws.*;
import org.basex.query.util.*;
import org.basex.query.value.type.*;

/**
 * Definitions of all built-in XQuery functions.
 * New namespace mappings for function prefixes and URIs must be added to the static initializer of
 * the {@link NSGlobal} class.
 *
 * @author BaseX Team 2005-20, BSD License
 * @author Christian Gruen
 */
public enum ApiFunction implements AFunction {

  // Geo Module

  /** XQuery function. */
  _GEO_AREA(GeoArea::new, "area(node)", arg(ELM_O), DBL_O, GEO_URI),
  /** XQuery function. */
  _GEO_AS_BINARY(GeoAsBinary::new, "as-binary(node)", arg(ELM_O), B64_O, GEO_URI),
  /** XQuery function. */
  _GEO_AS_TEXT(GeoAsText::new, "as-text(node)", arg(ELM_O), STR_O, GEO_URI),
  /** XQuery function. */
  _GEO_BOUNDARY(GeoBoundary::new, "boundary(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_BUFFER(GeoBuffer::new, "buffer(node,distance)", arg(ELM_O, DBL_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_CENTROID(GeoCentroid::new, "centroid(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_CONTAINS(GeoContains::new, "contains(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_CONVEX_HULL(GeoConvexHull::new, "convex-hull(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_CROSSES(GeoCrosses::new, "crosses(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_DIFFERENCE(GeoDifference::new, "difference(node1,node2)", arg(ELM_O, ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_DIMENSION(GeoDimension::new, "dimension(node)", arg(ELM_O), ITR_O, GEO_URI),
  /** XQuery function. */
  _GEO_DISJOINT(GeoDisjoint::new, "disjoint(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_DISTANCE(GeoDistance::new, "distance(node1,node2)", arg(ELM_O, ELM_O), DBL_O, GEO_URI),
  /** XQuery function. */
  _GEO_END_POINT(GeoEndPoint::new, "end-point(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_ENVELOPE(GeoEnvelope::new, "envelope(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_EQUALS(GeoEquals::new, "equals(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_EXTERIOR_RING(GeoExteriorRing::new, "exterior-ring(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_GEOMETRY_N(GeoGeometryN::new, "geometry-n(node,number)", arg(ELM_O, ITR_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_GEOMETRY_TYPE(GeoGeometryType::new, "geometry-type(node)", arg(ELM_O), QNM_O, GEO_URI),
  /** XQuery function. */
  _GEO_INTERIOR_RING_N(GeoInteriorRingN::new, "interior-ring-n(node,number)",
      arg(ELM_O, ITR_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_INTERSECTION(GeoIntersection::new, "intersection(node1,node2)",
      arg(ELM_O, ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_INTERSECTS(GeoIntersects::new, "intersects(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_IS_CLOSED(GeoIsClosed::new, "is-closed(node)", arg(ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_IS_RING(GeoIsRing::new, "is-ring(node)", arg(ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_IS_SIMPLE(GeoIsSimple::new, "is-simple(node)", arg(ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_LENGTH(GeoLength::new, "length(node)", arg(ELM_O), DBL_O, GEO_URI),
  /** XQuery function. */
  _GEO_NUM_GEOMETRIES(GeoNumGeometries::new, "num-geometries(node)", arg(ELM_O), ITR_O, GEO_URI),
  /** XQuery function. */
  _GEO_NUM_INTERIOR_RING(GeoNumInteriorRing::new, "num-interior-ring(node)",
      arg(ELM_O), ITR_O, GEO_URI),
  /** XQuery function. */
  _GEO_NUM_POINTS(GeoNumPoints::new, "num-points(node)", arg(ELM_O), ITR_O, GEO_URI),
  /** XQuery function. */
  _GEO_OVERLAPS(GeoOverlaps::new, "overlaps(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_POINT_N(GeoPointN::new, "point-n(node,number)", arg(ELM_O, ITR_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_POINT_ON_SURFACE(GeoPointOnSurface::new, "point-on-surface(node)",
      arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_RELATE(GeoRelate::new, "relate(node1,node2,matrix))",
      arg(ELM_O, ELM_O, STR_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_SRID(GeoSrid::new, "srid(node)", arg(ELM_O), URI_O, GEO_URI),
  /** XQuery function. */
  _GEO_START_POINT(GeoStartPoint::new, "start-point(node)", arg(ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_SYM_DIFFERENCE(GeoSymDifference::new, "sym-difference(node1,node2)",
      arg(ELM_O, ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_TOUCHES(GeoTouches::new, "touches(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_UNION(GeoUnion::new, "union(node1,node2)", arg(ELM_O, ELM_O), ELM_O, GEO_URI),
  /** XQuery function. */
  _GEO_WITHIN(GeoWithin::new, "within(node1,node2)", arg(ELM_O, ELM_O), BLN_O, GEO_URI),
  /** XQuery function. */
  _GEO_X(GeoX::new, "x(node)", arg(ELM_O), DBL_O, GEO_URI),
  /** XQuery function. */
  _GEO_Y(GeoY::new, "y(node)", arg(ELM_O), DBL_O, GEO_URI),
  /** XQuery function. */
  _GEO_Z(GeoZ::new, "z(node)", arg(ELM_O), DBL_O, GEO_URI),

  // Request Module

  /** XQuery function. */
  _REQUEST_ADDRESS(RequestAddress::new, "address()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_ATTRIBUTE(RequestAttribute::new, "attribute(name)",
      arg(STR_O), ITEM_ZM, flag(NDT), REQUEST_URI),
  /** XQuery function. */
  _REQUEST_ATTRIBUTE_NAMES(RequestAttributeNames::new, "attribute-names()",
      arg(), STR_ZM, flag(NDT), REQUEST_URI),
  /** XQuery function. */
  _REQUEST_CONTEXT_PATH(RequestContextPath::new, "context-path()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_COOKIE(RequestCookie::new, "cookie(name[,default])",
      arg(STR_O, STR_O), STR_ZO, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_COOKIE_NAMES(RequestCookieNames::new, "cookie-names()", arg(), STR_ZM, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_HEADER(RequestHeader::new, "header(name[,default])",
      arg(STR_O, STR_O), STR_ZO, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_HEADER_NAMES(RequestHeaderNames::new, "header-names()", arg(), STR_ZM, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_HOSTNAME(RequestHostname::new, "hostname()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_METHOD(RequestMethod::new, "method()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_PARAMETER(RequestParameter::new, "parameter(name[,default])",
      arg(STR_O, ITEM_ZM), ITEM_ZM, flag(NDT), REQUEST_URI),
  /** XQuery function. */
  _REQUEST_PARAMETER_NAMES(RequestParameterNames::new, "parameter-names()",
      arg(), STR_ZM, flag(NDT), REQUEST_URI),
  /** XQuery function. */
  _REQUEST_PATH(RequestPath::new, "path()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_PORT(RequestPort::new, "port()", arg(), ITR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_QUERY(RequestQuery::new, "query()", arg(), STR_ZO, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_REMOTE_ADDRESS(RequestRemoteAddress::new, "remote-address()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_REMOTE_HOSTNAME(RequestRemoteHostname::new, "remote-hostname()",
      arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_REMOTE_PORT(RequestRemotePort::new, "remote-port()", arg(), ITR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_SCHEME(RequestScheme::new, "scheme()", arg(), STR_O, REQUEST_URI),
  /** XQuery function. */
  _REQUEST_SET_ATTRIBUTE(RequestSetAttribute::new, "set-attribute(name,value)",
      arg(STR_O, ITEM_ZM), EMP, flag(NDT), REQUEST_URI),
  /** XQuery function. */
  _REQUEST_URI(RequestUri::new, "uri()", arg(), URI_O, REQUEST_URI),

  // RESTXQ Module

  /** XQuery function. */
  _RESTXQ_BASE_URI(RestBaseUri::new, "base-uri()", arg(), URI_O, REST_URI),
  /** XQuery function. */
  _RESTXQ_INIT(RestInit::new, "init([full])", arg(BLN_O), EMP, REST_URI),
  /** XQuery function. */
  _RESTXQ_URI(RestUri::new, "uri()", arg(), URI_O, REST_URI),
  /** XQuery function. */
  _RESTXQ_WADL(RestWadl::new, "wadl()", arg(), ELM_O, REST_URI),

  // Session Module

  /** XQuery function. */
  _SESSION_ACCESSED(SessionAccessed::new, "accessed()", arg(), DTM_O, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_CLOSE(SessionClose::new, "close()", arg(), EMP, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_CREATED(SessionCreated::new, "created()", arg(), DTM_O, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_DELETE(SessionDelete::new, "delete(key)", arg(STR_O), EMP, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_GET(SessionGet::new, "get(key[,default])",
      arg(STR_O, ITEM_ZM), ITEM_ZM, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_ID(SessionId::new, "id()", arg(), STR_O, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_NAMES(SessionNames::new, "names()", arg(), STR_ZM, flag(NDT), SESSION_URI),
  /** XQuery function. */
  _SESSION_SET(SessionSet::new, "set(key,value)", arg(STR_O, ITEM_ZM), EMP, flag(NDT), SESSION_URI),

  // Sessions Module

  /** XQuery function. */
  _SESSIONS_ACCESSED(SessionsAccessed::new, "accessed(id)",
      arg(STR_O), DTM_O, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_CLOSE(SessionsClose::new, "close(id,)", arg(STR_O), EMP, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_CREATED(SessionsCreated::new, "created(id)",
      arg(STR_O), DTM_O, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_DELETE(SessionsDelete::new, "delete(id,key)",
      arg(STR_O, STR_O), EMP, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_GET(SessionsGet::new, "get(id,key[,default])",
      arg(STR_O, STR_O, ITEM_ZM), ITEM_ZM, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_IDS(SessionsIds::new, "ids()", arg(), STR_ZM, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_NAMES(SessionsNames::new, "names(id)", arg(STR_O), STR_ZM, flag(NDT), SESSIONS_URI),
  /** XQuery function. */
  _SESSIONS_SET(SessionsSet::new, "set(id,key,value)",
      arg(STR_O, STR_O, ITEM_ZM), EMP, flag(NDT), SESSIONS_URI),

  // WebSocket Module

  /** XQuery function. */
  _WS_BROADCAST(WsBroadcast::new, "broadcast(message)", arg(ITEM_O), EMP, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_CLOSE(WsClose::new, "close(id)", arg(STR_O), EMP, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_DELETE(WsDelete::new, "delete(id,key)", arg(STR_O, STR_O), EMP, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_EMIT(WsEmit::new, "emit(message)", arg(ITEM_O), EMP, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_EVAL(WsEval::new, "eval(string[,bindings[,options]])",
      arg(STR_O, MAP_ZO, MAP_ZO), STR_O, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_GET(WsGet::new, "get(id,key[,default])",
      arg(STR_O, STR_O, ITEM_ZM), ITEM_ZM, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_ID(WsId::new, "id()", arg(), STR_O, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_IDS(WsIds::new, "ids()", arg(), STR_ZM, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_PATH(WsPath::new, "path(id)", arg(STR_O), STR_O, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_SEND(WsSend::new, "send(message[,ids])", arg(ITEM_O, STR_ZM), EMP, flag(NDT), WS_URI),
  /** XQuery function. */
  _WS_SET(WsSet::new, "set(id,key,value)", arg(STR_O, STR_O, ITEM_ZM), EMP, flag(NDT), WS_URI);

  /** Function definition. */
  private final FuncDefinition definition;

  /**
   * Constructs a function signature; calls
   * {@link #ApiFunction(Supplier, String, SeqType[], SeqType, EnumSet)}.
   * @param supplier function implementation constructor
   * @param desc descriptive function string
   * @param args types of the function arguments
   * @param seqType return type
   */
  ApiFunction(final Supplier<? extends StandardFunc> supplier, final String desc,
      final SeqType[] args, final SeqType seqType) {
    this(supplier, desc, args, seqType, EnumSet.noneOf(Flag.class));
  }

  /**
   * Constructs a function signature; calls
   * {@link #ApiFunction(Supplier, String, SeqType[], SeqType, EnumSet)}.
   * @param supplier function implementation constructor
   * @param desc descriptive function string
   * @param args types of the function arguments
   * @param type return type
   * @param uri uri
   */
  ApiFunction(final Supplier<? extends StandardFunc> supplier, final String desc,
      final SeqType[] args, final SeqType type, final byte[] uri) {
    this(supplier, desc, args, type, EnumSet.noneOf(Flag.class), uri);
  }

  /**
   * Constructs a function signature; calls
   * {@link #ApiFunction(Supplier, String, SeqType[], SeqType, EnumSet, byte[])}.
   * @param supplier function implementation constructor
   * @param desc descriptive function string
   * @param args types of the function arguments
   * @param seqType return type
   * @param flag static function properties
   */
  ApiFunction(final Supplier<? extends StandardFunc> supplier, final String desc,
      final SeqType[] args, final SeqType seqType, final EnumSet<Flag> flag) {
    this(supplier, desc, args, seqType, flag, FN_URI);
  }

  /**
   * Constructs a function signature.
   * @param supplier function implementation constructor
   * @param desc descriptive function string, containing the function name and its arguments in
   *   parentheses. Optional arguments are represented in nested square brackets; three dots
   *   indicate that the number of arguments of a function is not limited.
   * @param params parameter types
   * @param seqType return type
   * @param flags static function properties
   * @param uri uri
   */
  ApiFunction(final Supplier<? extends StandardFunc> supplier, final String desc,
      final SeqType[] params, final SeqType seqType, final EnumSet<Flag> flags, final byte[] uri) {
    definition = new FuncDefinition(supplier, desc, params, seqType, flags, uri);
  }

  @Override
  public FuncDefinition definition() {
    return definition;
  }

  /**
   * Returns an array representation of the specified sequence types.
   * @param arg arguments
   * @return array
   */
  private static SeqType[] arg(final SeqType... arg) {
    return arg;
  }

  /**
   * Returns a set representation of the specified compiler flags.
   * @param flags flags
   * @return set
   */
  private static EnumSet<Flag> flag(final Flag... flags) {
    final EnumSet<Flag> set = EnumSet.noneOf(Flag.class);
    Collections.addAll(set, flags);
    return set;
  }

  /**
   * Adds function signatures to the list. Called via reflection during initialization.
   * @param list list of function signatures
   */
  public static void init(final ArrayList<FuncDefinition> list) {
    for(final ApiFunction func : values()) list.add(func.definition);
  }

  @Override
  public final String toString() {
    return definition.toString();
  }
}
