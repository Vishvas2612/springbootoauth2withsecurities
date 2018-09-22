//package com.atishay.springboot.common.utils;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import com.hazelcast.nio.ObjectDataInput;
//import com.hazelcast.nio.ObjectDataOutput;
//import com.hazelcast.nio.serialization.StreamSerializer;
//
///***
// * This class acts as Serializer for java.util.Optional.
// * 
// * @author vishvas
// *
// */
//@SuppressWarnings("rawtypes")
//public class HazelCastOptionalSerializer implements StreamSerializer<Optional> {
//
//	@Override
//	public void write(ObjectDataOutput out, Optional object) throws IOException {
//		if (object.isPresent()) {
//			out.writeObject(object.get());
//		} else {
//			out.writeObject(null);
//		}
//	}
//
//	@Override
//	public Optional read(ObjectDataInput in) throws IOException {
//		Object result = in.readObject();
//		return result == null ? Optional.empty() : Optional.of(result);
//	}
//
//	@Override
//	public int getTypeId() {
//		return 1;
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//
//}
